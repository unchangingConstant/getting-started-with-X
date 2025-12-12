import { useState } from 'react'
import './App.css'
import Table from './components/Table'

function App() {

  const initRows = [
    'Somebody',
    'Once told me',
    'The world'
  ]

  const [rows, setRows] = useState(initRows);
  const [input, setInput] = useState('');

  const addEntry = (newEntry) => {
    setRows([...rows, newEntry]);
  }

  return (
    <>
      <h1>Table</h1>
        <div>
          <input 
            value={input} 
            onChange={(change) => setInput(change.target.value)} 
            id="entryInput"/>
        </div>
        <div id="controls">
          <button onClick={() => addEntry(input)} id="addButton">
            Add Entry
          </button>
        </div>
      <Table rows={rows}/>
    </>
  )
}

export default App
