package ddd

data class NotFoundDocument(val documentId: DocumentId) : GetDocumentResult {
    override fun documentId(): DocumentId =
        documentId

    override fun value(): Document? =
        null

    override fun exists(): Boolean =
        false
}
