package com.cabolabs.archetype

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

import com.cabolabs.ehrserver.ehr.clinical_documents.ArchetypeIndexItem
import com.cabolabs.ehrserver.ehr.clinical_documents.OperationalTemplateIndex
import com.cabolabs.ehrserver.ehr.clinical_documents.OperationalTemplateIndexItem

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([ArchetypeIndexItem, OperationalTemplateIndex, OperationalTemplateIndexItem]) // to allow calls to domain methods
class OperationalTemplateIndexerTests {

    void setUp() {
        // Setup logic here
    }

    void tearDown() {
        // Tear down logic here
    }

    void testSomething() {
    
        assert ArchetypeIndexItem.count() == 0
        assert OperationalTemplateIndex.count() == 0
        assert OperationalTemplateIndexItem.count() == 0
    
        def opti = new com.cabolabs.archetype.OperationalTemplateIndexer()
        opti.indexAll()
        
        // FIXME: this depends on the number of opts in the opts folder
        assert ArchetypeIndexItem.count() > 0
        assert OperationalTemplateIndex.count() > 0
        assert OperationalTemplateIndexItem.count() > 0
    }
}