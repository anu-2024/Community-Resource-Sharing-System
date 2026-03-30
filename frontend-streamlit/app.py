import streamlit as st
import sqlite3
import pandas as pd

st.set_page_config(page_title="Community Resource Sharing", layout="wide")

conn = sqlite3.connect("community.db", check_same_thread=False)
c = conn.cursor()

c.execute("""
CREATE TABLE IF NOT EXISTS items(
id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT,
category TEXT,
description TEXT,
condition TEXT,
location TEXT,
donor TEXT,
contact TEXT,
status TEXT
)
""")

conn.commit()

st.title("🌍 Community Resource Sharing System")

menu = st.sidebar.selectbox(
"Navigation",
["Home","Add Donation","Browse Items"]
)

# HOME PAGE
if menu == "Home":

    st.subheader("Reducing Waste. Helping Communities.")

    df = pd.read_sql("SELECT * FROM items", conn)

    total = len(df)
    available = len(df[df["status"]=="Available"]) if total>0 else 0

    col1,col2 = st.columns(2)

    col1.metric("Total Items Donated", total)
    col2.metric("Available Items", available)

    st.write(
    "Donate unused household items so others in the community can benefit."
    )

# ADD ITEM
elif menu == "Add Donation":

    st.subheader("Add Item for Donation")

    name = st.text_input("Item Name")
    category = st.selectbox(
    "Category",
    ["Books","Clothes","Furniture","Electronics","Other"]
    )

    description = st.text_area("Description")

    condition = st.selectbox(
    "Condition",
    ["New","Good","Used"]
    )

    location = st.text_input("Location")

    donor = st.text_input("Donor Name")

    contact = st.text_input("Contact Number")

    if st.button("Donate Item"):

        c.execute("""
        INSERT INTO items
        (name,category,description,condition,location,donor,contact,status)
        VALUES(?,?,?,?,?,?,?,?)
        """,
        (name,category,description,condition,location,donor,contact,"Available")
        )

        conn.commit()

        st.success("Item added successfully!")

# VIEW ITEMS
elif menu == "Browse Items":

    st.subheader("Available Items")

    category_filter = st.selectbox(
    "Filter by Category",
    ["All","Books","Clothes","Furniture","Electronics","Other"]
    )

    if category_filter == "All":
        df = pd.read_sql("SELECT * FROM items", conn)
    else:
        df = pd.read_sql(
        f"SELECT * FROM items WHERE category='{category_filter}'", conn)

    if df.empty:
        st.info("No items available.")
    else:

        for i,row in df.iterrows():

            with st.container():

                col1,col2 = st.columns([3,1])

                col1.markdown(f"""
                ### {row['name']}
                **Category:** {row['category']}  
                **Condition:** {row['condition']}  
                **Location:** {row['location']}  
                **Description:** {row['description']}  
                **Contact:** {row['contact']}
                """)

                if row["status"] == "Available":

                    if col2.button("Request", key=row["id"]):

                        c.execute(
                        "UPDATE items SET status='Requested' WHERE id=?",
                        (row["id"],)
                        )

                        conn.commit()

                        st.success("Request sent!")

                else:
                    col2.write("Requested")

                st.divider()
