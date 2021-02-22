package ddd

class DocumentPublisher {
    private fun publishLocked(storedDocument: StoredDocument) {

    }

    fun publish(result: LockResult): LockResult {
        when (result) {
            is LockSuccessfulResult -> this.publishLocked(result.doc)
            else -> Unit
        }
        return result
    }
}
