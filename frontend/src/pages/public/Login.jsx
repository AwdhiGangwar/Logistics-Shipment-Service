import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { useAuth } from '../../context/AuthContext'
import Input from '../../components/Input'
import Button from '../../components/Button'

const Login = () => {
  const { login } = useAuth()
  const [form, setForm] = useState({ email: '', password: '' })
  const [error, setError] = useState(null)
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value })

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError(null)
    setLoading(true)
    try {
      await login(form)
      navigate('/')
    } catch (err) {
      setError(err?.response?.data?.message || 'Login failed')
      setLoading(false)
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center">
      <div className="w-full max-w-md bg-white p-8 rounded-md shadow">
        <h2 className="text-2xl font-semibold mb-4">Sign in</h2>
        {error && <div className="text-sm text-red-600 mb-2">{error}</div>}
        <form onSubmit={handleSubmit} className="space-y-4">
          <Input name="email" label="Email" type="email" value={form.email} onChange={handleChange} />
          <Input name="password" label="Password" type="password" value={form.password} onChange={handleChange} />
          <Button type="submit" disabled={loading}>{loading ? 'Signing...' : 'Sign in'}</Button>
        </form>
        <div className="mt-4 text-sm">Don't have an account? <Link to="/register" className="text-blue-600">Register</Link></div>
      </div>
    </div>
  )
}

export default Login
