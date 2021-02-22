package ddd

class LockAccessChecker(val user: User) {
    fun canLock(document: StoredDocument): Boolean =
        document.value().lockedBy == null

    fun alreadyLocked(document: StoredDocument): Boolean =
        document.value().lockedBy == this.user.id
}
