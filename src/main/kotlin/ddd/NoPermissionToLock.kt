package ddd

data class NoPermissionToLock(val doc: StoredDocument): LockResult {
    override fun document(): Document =
        this.doc.value()
}
