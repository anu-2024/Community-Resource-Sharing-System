
import streamlit as st
import requests
import pandas as pd

API="http://localhost:8080/api/items"

st.set_page_config(page_title="Community Resource Sharing",layout="wide")

st.title("Community Resource Sharing System")

menu=st.sidebar.selectbox("Menu",
["Home","Add Item","View Items"])

if menu=="Home":
    st.header("Welcome")
    st.write("Donate or request useful household items to reduce waste.")

if menu=="Add Item":
    st.header("Add Donation Item")

    name=st.text_input("Item Name")
    category=st.selectbox("Category",["Books","Clothes","Furniture","Electronics","Other"])
    description=st.text_area("Description")
    condition=st.selectbox("Condition",["New","Good","Used"])
    location=st.text_input("Location")
    donor=st.text_input("Donor Name")
    contact=st.text_input("Contact")

    if st.button("Submit"):
        data={
        "name":name,
        "category":category,
        "description":description,
        "condition":condition,
        "location":location,
        "donorName":donor,
        "contact":contact
        }
        r=requests.post(API,json=data)
        st.success("Item Added!")

if menu=="View Items":
    st.header("Available Items")

    r=requests.get(API)
    data=r.json()

    if data:
        df=pd.DataFrame(data)
        st.dataframe(df)
