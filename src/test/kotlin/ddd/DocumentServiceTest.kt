package ddd

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class DocumentServiceTest {
    private val user = User(id = 2, name = "Lars")
    private val publisher = DocumentPublisher()

    @Test
    fun `should successfully lock a document`() {
        val document =
            Document(
                lockedBy = null,
                id = 1,
                status = DocumentStatus.RECEIVED)
        val expectedDocument =
            Document(
                lockedBy = document.lockedBy,
                id = document.id,
                status = DocumentStatus.LOCKED)
        val documentGetter =
            object : DocumentGetter {
                override fun getById(id: DocumentId): GetDocumentResult =
                    StoredDocument(document)
            }
        val documentUpdater =
            object : DocumentUpdater {
                override fun update(document: DocumentUpdate): StoredDocument =
                    StoredDocument(expectedDocument)
            }
        val userGetter =
            object : UserGetter {
                override fun getById(id: UserId): User =
                    user
            }

        val service = DocumentService(documentGetter, documentUpdater, userGetter, publisher)
        assertEquals(
            LockSuccessfulResult(StoredDocument(expectedDocument)),
            service.lockDocument(document.id, user.id))
    }


    @Test
    fun `should not lock a document, the document doesn't exist`() {
        val documentId = 1
        val documentGetter =
            object : DocumentGetter {
                override fun getById(id: DocumentId): GetDocumentResult =
                    NotFoundDocument(id)
            }
        val documentUpdater =
            object : DocumentUpdater {
                override fun update(document: DocumentUpdate): StoredDocument =
                    TODO()
            }
        val userGetter =
            object : UserGetter {
                override fun getById(id: UserId): User =
                    user
            }

        val service = DocumentService(documentGetter, documentUpdater, userGetter, publisher)
        assertEquals(
            NotFoundDocumentResult(documentId),
            service.lockDocument(documentId, user.id))
    }
}
