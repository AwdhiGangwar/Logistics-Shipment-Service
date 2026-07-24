import React, { useState } from 'react'
import Input from '../components/Input'
import Button from '../components/Button'
import trackingService from '../services/trackingService'

const TrackShipment = () => {
  const [tracking, setTracking] = useState('')
  const [timeline, setTimeline] = useState(null)
  const [error, setError] = useState(null)
  const [loading, setLoading] = useState(false)

  const handleSearch = async (e) => {
    e.preventDefault()
    setError(null)
    setLoading(true)
    try {
      const res = await trackingService.getTimeline(tracking)
      setTimeline(res)
    } catch (err) {
      setError(err?.response?.data?.message || 'Not found')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container max-w-2xl">
      <h2 className="text-xl font-semibold mb-4">Track Shipment</h2>
      <form onSubmit={handleSearch} className="flex gap-3 mb-4">
        <Input name="tracking" value={tracking} onChange={e => setTracking(e.target.value)} placeholder="Enter tracking number" />
        <Button type="submit" disabled={loading}>{loading ? 'Searching...' : 'Search'}</Button>
      </form>

      {error && <div className="text-red-600 mb-2">{error}</div>}

      {timeline && (
        <div className="bg-white p-4 rounded-md shadow">
          <h3 className="font-semibold mb-2">Timeline</h3>
          <ol className="border-l pl-4">
            {timeline.events.map((ev, i) => (
              <li key={i} className="mb-3">{ev.status} <div className="text-xs text-gray-500">{ev.date}</div></li>
            ))}
          </ol>
        </div>
      )}
    </div>
  )
}

export default TrackShipment
