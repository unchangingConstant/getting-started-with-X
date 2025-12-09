'use strict';

class TableContent {

  #changeHandler;
  #columnNames;
  #rows;

  constructor({
    changeHandler = ()=>{}, 
    initRows = [], 
    columnNames = []} = {}) {
    this.#changeHandler = changeHandler;
    this.#columnNames = columnNames;
    this.#rows = initRows;   
  }

  getRows() {
    return this.#rows;
  }

  addRow(newRow) {
    this.#rows = [...this.#rows, TableContent.#formatRow(newRow, this.#columnNames)];
    this.#changeHandler(this.#rows);
  }

  delRow() {
    this.#rows.pop();
    this.#changeHandler(this.#rows);
  }

  static #formatRow(row, columnNames) {
    const result = {};
    for (const column of columnNames) {
      result[column] = row[column] !== undefined ? row[column] : null;
    }
    return result;
  }

}

// Create model
var tableModel = new TableContent({
  changeHandler: updateTable,
  columnNames: ['fullName', 'subjects']
});

// Controller
function onAdd() {
  tableModel.addRow({
    fullName : 'Sample',
    subjects : 1
  });
}

function onDelete() {
  tableModel.delRow()
}

// View
function updateTable(newRows) {
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

