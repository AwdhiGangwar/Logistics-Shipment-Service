import React from 'react'

const Table = ({ columns = [], data = [], renderRowActions }) => {
  return (
    <div className="overflow-x-auto bg-white rounded-md shadow-sm">
      <table className="w-full text-sm">
        <thead className="bg-gray-50 text-left">
          <tr>
            {columns.map((c) => (
              <th key={c.key} className="px-4 py-3">{c.title}</th>
            ))}
            {renderRowActions && <th className="px-4 py-3">Action</th>}
          </tr>
        </thead>
        <tbody>
          {data.map((row) => (
            <tr key={row.id} className="border-t">
              {columns.map((c) => (
                <td key={c.key} className="px-4 py-3">{row[c.key]}</td>
              ))}
              {renderRowActions && <td className="px-4 py-3">{renderRowActions(row)}</td>}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default Table
