package ddd

interface GetDocumentResult {
  fun documentId(): DocumentId
  fun value(): Document?
  fun exists(): Boolean
}
