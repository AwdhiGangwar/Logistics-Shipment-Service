import { authApi } from './api'

const authService = {
  login: async ({ email, password }) => {
    const res = await authApi.post('/api/auth/login', { email, password })
    return res.data
  },
  register: async ({ name, email, password }) => {
    const res = await authApi.post('/api/auth/register', { name, email, password })
    return res.data
  }
}

export default authService
