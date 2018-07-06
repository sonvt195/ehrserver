
/*
 * Copyright 2011-2017 CaboLabs Health Informatics
 *
 * The EHRServer was designed and developed by Pablo Pazos Gutierrez <pablo.pazos@cabolabs.com> at CaboLabs Health Informatics (www.cabolabs.com).
 *
 * You can't remove this notice from the source code, you can't remove the "Powered by CaboLabs" from the UI, you can't remove this notice from the window that appears then the "Powered by CaboLabs" link is clicked.
 *
 * Any modifications to the provided source code can be stated below this notice.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cabolabs.util

import java.util.Date
import java.util.TimeZone
import grails.util.Holders
import java.text.ParseException
import java.text.SimpleDateFormat

class DateParser {

   static def config = Holders.config.app
   
   static Date tryParse(String dateString)
   {
      if (!dateString)
      {
         throw new IllegalArgumentException('dateString is null')
      }
      
      //println "tryParse "+ dateString
      def supported_formats = [
         
         // datetime formats
         config.l10n.datetime_format,
         config.l10n.ext_datetime_format,
         config.l10n.ext_datetime_format_point,
         config.l10n.ext_datetime_utcformat,
         config.l10n.ext_datetime_utcformat_point,
         
         // no fraction
         config.l10n.datetime_format_nof,
         config.l10n.ext_datetime_format_nof,
         config.l10n.ext_datetime_utcformat_nof,
         
         // date formats
         config.l10n.date_format,
         config.l10n.display_date_format,
         config.l10n.db_date_format
      ]
      
      def d
      for (String format : supported_formats)
      {
         try
         {
            SimpleDateFormat sdf = new SimpleDateFormat(format)
            sdf.setLenient(false) // avoids heuristic parsing, enabling just exact parsing
            
            // If the date ends in Z, it's timezone is UTC
            // It the TZ is not present, the parser sets the local timezone and should be UTC.
            // This forces to use UTC.
            if (dateString.endsWith('Z'))
            {
               sdf.setTimeZone(TimeZone.getTimeZone("UTC"))
            }

            d = sdf.parse(dateString)
            
            //println "match ${dateString} ${format} ${d} "+ d.getTimezoneOffset()
            
            return d
         }
         catch (ParseException e) {}
      }

      return null
   }
}
