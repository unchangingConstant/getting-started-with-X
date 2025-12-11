'use strict'

export class TableContent {

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