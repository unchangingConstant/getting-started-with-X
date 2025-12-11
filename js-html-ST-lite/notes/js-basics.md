# JavaScript Basics

## Variables

### `let`
These variables have block scope. They can be reassigned but NOT redeclared.

```js
let x = 1;
let x = "Hello"; // NOT allowed
```

### `const`

These variables have block scope. They can not be reassigned.

### `var` (not recommended):

These variables have global scope. They are "hoisted", meaning that they can be used as long as they're declared at some point in the file.

```js
console.log(text); // Valid
var text = "Hello, world!";
```

### automatic (not recommended):

```js
cum = "cum" // ew
```

## Datatypes

- String:	A text of characters enclosed in quotes
- Number:	A number representing a mathematical value (Always 64-bit floating point)
- Bigint:	A number representing a large integer
- Boolean:	A data type representing true or false
- Object:	A collection of key-value pairs of data
- Undefined:	A primitive variable with no assigned value
- Null:	A primitive value representing object absence
- Symbol:	A unique and primitive identifier

## Objects

Objects are collections of key-value pairs, where each key (known as property names) has a value.

In JavaScript, Objects are King. If you Understand Objects, you Understand JavaScript. In JavaScript, almost "everything" is an object. All JavaScript values, except primitives, are objects.

### Methods

Object methods are function definitions stored as property values.

```js
const person = {
  firstName: "John",
  lastName : "Doe",
  id       : 5566,
  fullName : function() {
    return this.firstName + " " + this.lastName;
  }
};
```

### Constructors

To create an object type we use an object constructor function. It is considered good practice to name constructor functions with an upper-case first letter.

```js
// This is an object type definition
function Person(first, last, age, eye) {
  this.firstName = first;
  this.lastName = last;
  this.age = age;
  this.eyeColor = eye;
}
```

## HTML Events

Here are some examples of HTML events:

- An HTML web page has finished loading
- An HTML input field was changed
- An HTML button was clicked
