package ddd

interface UserGetter {
  fun getById(id: UserId): User
}
