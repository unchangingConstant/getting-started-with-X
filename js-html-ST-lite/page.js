'use strict';

import {TableContent} from './models/TableContent.js'

// Create model
var tableModel = new TableContent({
  changeHandler: renderTable,
  columnNames: ['fullName', 'subjects']
});

// Controller
function onAdd(fullName, subjects) {
  tableModel.addRow({
    fullName : fullName,
    subjects : subjects
  });
}

function onDelete() {
  tableModel.delRow()
}

document.getElementById('addButton').addEventListener("click", () => {
  const fullName = document.getElementById('nameInput').value
  const subjects = document.getElementById('subjectsInput').value
  onAdd(fullName, subjects);
});
document.getElementById('deleteButton').addEventListener("click", onDelete)

// View
function renderTable(newRows) {
  const htmlTable = document.getElementById("table");
  htmlTable.innerHTML = '';
  for (const row of newRows) {
    const htmlRow = document.createElement('tr');
    for (const key of Object.keys(row)) {
      const htmlTableItem = document.createElement('td');
      htmlTableItem.textContent = row[key]
      htmlRow.appendChild(htmlTableItem);
    }
    htmlTable.appendChild(htmlRow);
  }
}

console.log("Run!")

