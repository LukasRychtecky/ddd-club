package ddd

class DocumentLocker(
    private val updater: DocumentUpdater,
    private val user: User,
    private val lockAccessChecker: LockAccessChecker
    ) {

    private fun lockStored(doc: StoredDocument): LockResult =
        when {
            lockAccessChecker.alreadyLocked(doc) -> {
                AlreadyLockedResult(doc)
            }
            lockAccessChecker.canLock(doc) -> {
                val update = DocumentUpdate.fromStoredDocument(
                    doc, user.id, DocumentStatus.LOCKED)
                LockSuccessfulResult(updater.update(update.copy(lockedBy = user.id)))
            }
            else -> {
                NoPermissionToLock(doc)
            }
        }

    fun lock(doc: GetDocumentResult): LockResult =
        when (doc) {
            is StoredDocument -> this.lockStored(doc)
            else -> NotFoundDocumentResult(doc.documentId())
        }
}
