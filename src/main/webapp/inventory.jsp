<!--
 A quick-and-dirty faking up of an inventory page.
 -->
<style>
<!--H2{font-family : sans-serif,Arial,Tahoma;color : white;background-color : #0086b2;}
</style>
<head>
<title>Current Inventory</title>
</head>
<body bgcolor=white>
<h1>Current Inventory</h1>
<h2>Company Confidential!!</h2>
<p>Here is the sales/inventory situation as of
<%= new java.util.Date() %>
</p>
<table border=1>
<tr>
	<th>Part<br>Number<th>Part<br>Name
		<th>In hand<th>Committed but<br>not shipped<th>Position</th>
</tr>
<%
	for (int i=0; i<data.length; i++) {
 %>
<tr>
	<td><%=data[i].getPartNum()%>
	<td><%=data[i].getName()%>
	<td><%=data[i].getQtyOnHand()%>
	<td><%=data[i].getQtyCommitted()%>
	<td <%=data[i].getQtyAvail()<0?"bgcolor='red'":""%>>
		<%=data[i].getQtyAvail()%>
	</td>
</td>
<%
 }
 %>
</table>
<%!	
	// In real life the data would be fetched from a database
	InventoryItem[] data = {
		new InventoryItem( 123, "Widget", 211, 15 ),
		new InventoryItem( 207, "Hinge", 100, 0 ),
		new InventoryItem( 243, "Flap", 100, 0 ),
		new InventoryItem( 257, "Bolt", 1000, 100 ),
		new InventoryItem( 263, "Nut", 900, 1000 ),
	};
	/* This should be an EJB :-) */
	class InventoryItem {
		int partNumber;
		String name;
		int oh, committed;
		InventoryItem(int pnum, String name, int oh, int comm) {
			partNumber = pnum;
			this.name = name;
			this.oh = oh;
			committed = comm;
		}
		int getPartNum() { return partNumber; }
		String getName() { return name; }
		int getQtyOnHand() { return oh; }
		int getQtyCommitted() { return committed; }
		int getQtyAvail() { return oh - committed; }
	}
 %>
