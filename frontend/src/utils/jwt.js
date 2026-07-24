export const decodeToken = (token) => {
  if (!token) return null
  try {
    const [, payload] = token.split('.')
    const decoded = JSON.parse(atob(payload.replace(/-/g, '+').replace(/_/g, '/')))
    return decoded
  } catch {
    return null
  }
}

export const getUserIdFromToken = (token) => {
  const decoded = decodeToken(token)
  return decoded?.userId || null
}
