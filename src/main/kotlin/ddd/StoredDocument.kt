package ddd

data class StoredDocument(val document: Document) : GetDocumentResult {
    override fun documentId(): DocumentId =
        document.id

    override fun value(): Document =
        document

    override fun exists(): Boolean =
        true
}
