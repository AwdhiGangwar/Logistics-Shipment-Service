import React, { useEffect, useState } from 'react'
import { useAuth } from '../context/AuthContext'
import userService from '../services/userService'
import Input from '../components/Input'
import Button from '../components/Button'

const Profile = () => {
  const { user } = useAuth()
  const [profile, setProfile] = useState({ fullName: '', phoneNumber: '', address: '' })
  const [loading, setLoading] = useState(false)
  const [message, setMessage] = useState(null)

  useEffect(() => {
    ;(async () => {
      try {
        if (user?.id) {
          const res = await userService.getProfile(user.id)
          setProfile({
            fullName: res.fullName || '',
            phoneNumber: res.phoneNumber || '',
            address: res.address || ''
          })
        }
      } catch (e) {}
    })()
  }, [user])

  const handleChange = (e) => setProfile({ ...profile, [e.target.name]: e.target.value })

  const handleSubmit = async (e) => {
    e.preventDefault()
    setLoading(true)
    try {
      await userService.updateProfile(user?.id, profile)
      setMessage('Profile updated')
    } catch (e) {
      setMessage('Failed to update')
    } finally { setLoading(false) }
  }

  return (
    <div className="container max-w-md">
      <h2 className="text-xl font-semibold mb-4">Profile</h2>
      {message && <div className="mb-2 text-sm text-gray-700">{message}</div>}
      <form onSubmit={handleSubmit} className="space-y-4 bg-white p-4 rounded-md shadow">
        <Input name="fullName" label="Full Name" value={profile.fullName} onChange={handleChange} />
        <Input name="phoneNumber" label="Phone Number" value={profile.phoneNumber} onChange={handleChange} />
        <Input name="address" label="Address" value={profile.address} onChange={handleChange} />
        <Button type="submit" disabled={loading}>{loading ? 'Saving...' : 'Save'}</Button>
      </form>
    </div>
  )
}

export default Profile
