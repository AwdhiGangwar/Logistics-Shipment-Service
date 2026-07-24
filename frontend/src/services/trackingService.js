import { trackingApi } from './api'

const trackingService = {
  getTimeline: async (trackingNumber) => {
    const res = await trackingApi.get(`/api/tracking/tracking-number/${trackingNumber}`)
    return res.data
  }
}

export default trackingService
