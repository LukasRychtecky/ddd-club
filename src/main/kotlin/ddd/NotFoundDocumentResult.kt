package ddd

data class NotFoundDocumentResult(val documentId: DocumentId) : LockResult {
    override fun document(): Document? =
        null
}
