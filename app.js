const express = require('express');
const mongoose = require('mongoose');
const dotenv = require('dotenv');

const app = express();
dotenv.config();

app.set('port', process.env.PORT || 3000);

app.get('/', (req, res) => {
    res.send('Hello, Express')
})

app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중')
})

try {
    mongoose.connect(process.env.DB_URI, { useNewUrlParser: true });
    mongoose.connection.once("open", () => {
      console.log("MongoDB is Connected");
    });
  } catch (error) {
    console.error("mongoDB error");
    console.log(error);
  }