package ddd

interface DocumentGetter {
  fun getById(id: DocumentId): GetDocumentResult
}
