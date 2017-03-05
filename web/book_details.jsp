<%@page contentType="text/html" pageEncoding="UTF-8" import="store.*, java.util.*"%>
<jsp:useBean id="tags" scope="page" class="store.CommonTags" />
<jsp:useBean id="book" scope="session" class="store.Book" />
<jsp:useBean id="cart" scope="session" class="store.BookSet" />
<jsp:useBean id="customer" scope="session" class="store.Customer" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=book.getTitle()%></title>
        <%=tags.getCss()%>
    </head>
    <body>
        <div id="header">
            <%=tags.getHeader()%>
            <%
                if (customer.getEmail() == null) {
                    out.println(tags.getCustomerForm());
                } else {
                    out.println(tags.getLoggedCustomerForm(customer.getEmail()));
                }
            %>
        </div>
        <div id="cart">
            <%=tags.getCartView()%>
            <div align="center"><font color="white" size="4"><i>Książki w koszyku: <b><%=cart.getBookCount()%></b></i></font></div>
        </div>
        <div id="form">
            <table>
                <tr>
                    <td>
                        <img src="images/<%=book.getIsbn()%>.jpg" 
                             alt="<%=book.getTitle()%>"
                             height="200" width="160">
                        <div align="center"><%=tags.addToCartForm(book.getIsbn())%></div>
                    </td>
                    <%=tags.getColumnSpace(5)%>
                    <td>
                        Tytuł: <b><%=book.getTitle()%></b><br>
                        Autor: 
                        <%
                            try {
                                Store store = new Store();
                                String authors = new String(store.getBookAuthors(book.getIsbn()));
                                out.println(authors);
                            } catch (Exception e) {}
                        %>
                        <br>
                        ISBN: <%=book.getIsbn()%><br>
                        Wydawca: <%=book.getPublisher()%><br>
                        Data wydania: <%=book.getPublicationDate()%><br>
                        Typ okładki: <%=book.getBookType()%><br>
                        Kategoria: 
                        <%
                            try {
                                Store store = new Store();
                                String categories = new String(store.getBookCategories(book.getIsbn()));
                                out.println(categories);
                            } catch (Exception e) {}
                        %>
                        <br>
                        Cena: <b><%=book.getPrice()%>zł</b>
                    </td>
                </tr>
                <%=tags.getRowSpace(20)%>
            </table>
            <%=book.getDescription()%>
        </div>
        <%=tags.getCategoriesForm()%>
    </body>
</html>
