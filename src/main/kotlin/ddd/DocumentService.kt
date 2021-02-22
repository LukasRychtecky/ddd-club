package ddd

class DocumentService(
    private val documentGetter: DocumentGetter,
    private val documentUpdater: DocumentUpdater,
    private val userService: UserGetter,
    private val publisher: DocumentPublisher
    ) {

    fun lockDocument(documentId: DocumentId, userId: UserId): LockResult {
        val user = userService.getById(userId)
        return publisher.publish(
            DocumentLocker(
                documentUpdater,
                user,
                LockAccessChecker(user)).lock(documentGetter.getById(documentId)))
    }
}
