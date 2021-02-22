package ddd

data class Document(
    val lockedBy: UserId?,
    val id: DocumentId,
    val status: DocumentStatus)
