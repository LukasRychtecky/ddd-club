package ddd

data class LockSuccessfulResult(val doc: StoredDocument) : LockResult {
    override fun document(): Document =
        this.doc.value()
}
