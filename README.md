# Shoes!

##### Add, List, Edit and Delete Shoe Stores and the Brands They Carry 9/5/15

#### By Aimee Reiss

## Description

This app will allow you to add the names of shoe stores and as well as shoe brands.  You can see the list of shoe stores and brands on their respective pages.  You can also click on an individual store to update or delete it as well as see what brands it carries and have the option of adding and deleting brands.  You can also look at each brand to see which stores are carrying it and add more stores.


## Setup

For the Database: <br>
In PSQL: <br>
CREATE DATABASE shoes; <br>
CREATE TABLE stores (id serial PRIMARY KEY, name varchar); <br>
CREATE TABLE brands (id serial PRIMARY KEY, label varchar); <br>
CREATE TABLE brands_stores (id serial PRIMARY KEY, brand_id int, store_id int); <br>
For testing you can also include: <br>
CREATE DATABASE shoes_test WITH TEMPLATE shoes; <br>

## Technologies Used

-Gradle <br>
-Spark <br>
-fluentlenium <br>
-Postgres <br>
-Java <br>


### Legal

Copyright (c) 2015 **_{Aimee Reiss}_**

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
