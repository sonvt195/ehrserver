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

package com.cabolabs.ehrserver.query

import com.cabolabs.ehrserver.ehr.clinical_documents.ArchetypeIndexItem

/**
 * SELECT archId/path
 * 
 * Para consultas de datos (queryData)
 * 
 * @author Pablo Pazos Gutierrez <pablo.pazos@cabolabs.com>
 */
class DataGet {
   
   String archetypeId
   String path
   String rmTypeName
   boolean allowAnyArchetypeVersion = false

   
   // TODO: poner name para mostrar en la definicion
   //       de la consulta, se saca de ArchetypeIndexItem o del
   //       arquetipo archetypeId para la path (que
   //       tiene el nodeId)
   
   static belongsTo = [Query]
   
   static transients = ['indexItem']
   
   ArchetypeIndexItem getIndexItem()
   {
      if (this.allowAnyArchetypeVersion)
         return ArchetypeIndexItem.findByArchetypeIdLikeAndPathAndRmTypeName(this.archetypeId+'%', this.path, this.rmTypeName)
      else
         return ArchetypeIndexItem.findByArchetypeIdAndPathAndRmTypeName(this.archetypeId, this.path, this.rmTypeName)
   }
}
