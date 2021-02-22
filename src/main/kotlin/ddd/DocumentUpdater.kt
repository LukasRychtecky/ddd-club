package ddd

interface DocumentUpdater {
    fun update(document: DocumentUpdate): StoredDocument
}
