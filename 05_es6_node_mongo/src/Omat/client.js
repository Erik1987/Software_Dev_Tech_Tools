const fetch = require('node-fetch');

async function getUsersAsync() {
    let response = await fetch('https://jsonplaceholder.typicode.com/users');
    let jsonData = await response.json();
    return jsonData;
}
async function getPostsAsync() {
    let response = await fetch('https://jsonplaceholder.typicode.com/posts');
    let jsonData = await response.json();
    return jsonData;
}
module.exports = {getUsersAsync, getPostsAsync};