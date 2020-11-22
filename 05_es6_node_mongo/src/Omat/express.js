// https://www.npmjs.com/package/express
const { json } = require('body-parser');
const express = require('express');
const app = express();

const {getUsersAsync, getPostsAsync} = require('./client');
 
app.get('/users', function (req, res) {
  
    getUsersAsync().then( users => {
        
        res.json(users);
        //console.log(users);
    });
    //let data = req.json("https://jsonplaceholder.typicode.com/users")  
});
app.get('/posts', function (req, res) {

    getPostsAsync().then( posts => {
        res.json(posts);
    });
});
app.get('/userPosts', function (req, res) {
    getUsersAsync().then (users => {
        //res.json(users);
        getPostsAsync().then (posts => {
            const mapped = users.map(u => ({user: u, posts: posts.filter(p => p.userId === u.id)}))
            res.json(mapped);
        });
    });
}); 
app.listen(3000, () => console.log("Running... "));