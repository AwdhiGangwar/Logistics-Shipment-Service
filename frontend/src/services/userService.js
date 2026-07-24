import { userApi } from './api'

const userService = {
  getProfile: async (userId) => {
    const res = await userApi.get(`/api/users/${userId}`)
    return res.data
  },
  updateProfile: async (userId, payload) => {
    const res = await userApi.put(`/api/users/${userId}`, payload)
    return res.data
  }
}

export default userService
