import axios from 'axios'

const createApi = (baseURL) => {
  const instance = axios.create({
    baseURL,
    headers: {
      'Content-Type': 'application/json'
    }
  })

  instance.interceptors.response.use(
    (res) => res,
    (err) => {
      if (err.response && err.response.status === 401) {
        if (typeof logoutHandler === 'function') logoutHandler()
      }
      return Promise.reject(err)
    }
  )

  return instance
}

const authApi = createApi(import.meta.env.VITE_API_AUTH_URL || '')
const userApi = createApi(import.meta.env.VITE_API_USER_URL || '')
const shipmentApi = createApi(import.meta.env.VITE_API_SHIPMENT_URL || '')
const trackingApi = createApi(import.meta.env.VITE_API_TRACKING_URL || '')

let logoutHandler = null

export const attachToken = (token) => {
  [userApi, shipmentApi, trackingApi].forEach((instance) => {
    if (token) {
      instance.defaults.headers.common['Authorization'] = `Bearer ${token}`
    } else {
      delete instance.defaults.headers.common['Authorization']
    }
  })
}

export const onUnauthorized = (cb) => {
  logoutHandler = cb
}

export { authApi, userApi, shipmentApi, trackingApi }
