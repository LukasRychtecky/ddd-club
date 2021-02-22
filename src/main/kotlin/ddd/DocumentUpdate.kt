package ddd

data class DocumentUpdate(
    val id: DocumentId,
    val userId: UserId,
    val status: DocumentStatus,
    val lockedBy: UserId? = null
    ) {
    companion object {
        fun fromStoredDocument(
            doc: StoredDocument,
            userId: UserId,
            status: DocumentStatus
        ): DocumentUpdate =
            DocumentUpdate(doc.value().id, userId, status)
    }
}
