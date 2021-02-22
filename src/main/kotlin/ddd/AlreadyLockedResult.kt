package ddd

data class AlreadyLockedResult(val doc: StoredDocument): LockResult {
    override fun document(): Document =
        this.doc.value()
}
