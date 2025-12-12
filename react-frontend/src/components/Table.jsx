import { useState } from 'react'

function Table({rows}) {

    // TODO, find a way to generate unique keys for each element. 
    // Reacts needs this for some reason????
    return (
        <table>
            <tbody>
        {rows.map((data, index) => {
            return (
                <tr key={index}>
                    <td>{data}</td>
                </tr>);
        })}
            </tbody>
        </table>
    );
    
}

export default Table;