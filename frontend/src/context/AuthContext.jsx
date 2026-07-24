import React, { createContext, useContext, useEffect, useState } from 'react'
import authService from '../services/authService'
import { attachToken, onUnauthorized } from '../services/api'
import { useNavigate } from 'react-router-dom'
import { getUserIdFromToken } from '../utils/jwt'

const AuthContext = createContext(null)

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(() => {
    try {
      return JSON.parse(localStorage.getItem('user')) || null
    } catch {
      return null
    }
  })
  const [token, setToken] = useState(() => localStorage.getItem('token'))
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()

  useEffect(() => {
    attachToken(token)
  }, [token])

  useEffect(() => {
    onUnauthorized(() => {
      logout()
    })
  }, [])

  const login = async (credentials) => {
    setLoading(true)
    try {
      const data = await authService.login(credentials)
      const userData = {
        id: getUserIdFromToken(data.token),
        email: data.email,
        name: data.name,
        role: data.role
      }
      setToken(data.token)
      setUser(userData)
      localStorage.setItem('token', data.token)
      localStorage.setItem('user', JSON.stringify(userData))
      setLoading(false)
      return data
    } catch (err) {
      setLoading(false)
      throw err
    }
  }

  const register = async (payload) => {
    setLoading(true)
    try {
      const data = await authService.register(payload)
      setLoading(false)
      return data
    } catch (err) {
      setLoading(false)
      throw err
    }
  }

  const logout = () => {
    setToken(null)
    setUser(null)
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    navigate('/login')
  }

  const value = {
    user,
    token,
    loading,
    login,
    register,
    logout,
    setUser
  }

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export const useAuth = () => useContext(AuthContext)
