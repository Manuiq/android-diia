package ua.gov.diia.documents.data.repository

import ua.gov.diia.diia_storage.store.datasource.DataSource
import ua.gov.diia.documents.models.DiiaDocument
import ua.gov.diia.documents.models.DiiaDocumentWithMetadata
import ua.gov.diia.documents.models.DocOrder
import ua.gov.diia.documents.models.TypeDefinedDocOrder

interface DocumentsDataRepository : DataSource<List<DiiaDocumentWithMetadata>> {

    fun updateDocOrder(docTypeList: List<String>)

    fun attachExternalDocument(document: DiiaDocumentWithMetadata)

    fun removeDocument(diiaDocument: DiiaDocument)

    fun updateDocument(diiaDocument: DiiaDocument)

    fun replaceExpDateByType(types: List<String>)

    fun saveDocTypeOrder(docOrders: List<DocOrder>)

    fun saveDocOrderForSpecificType(
        docOrders: List<TypeDefinedDocOrder>,
        docType: String
    )

    suspend fun getDocsByType(type: String): List<DiiaDocument?>?

    suspend fun loadLocalDocData(): List<DiiaDocumentWithMetadata>?

    suspend fun clear()

}
