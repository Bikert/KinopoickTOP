<%@ page import="ru.bikert.test_task.abstractions.models.Rating" %>
<%@ page import="java.util.List" %>
<html xmlns="http://www.w3.org/1999/xhtml"
<%--      xmlns:th="http://www.thymeleaf.org"--%>
<%--      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"--%>
<%--      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"--%>
>
<!DOCTYPE html>

<head>
    <title>КиноПоиск</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <h2>ТОП 20 фильмов по версии кинопоиска</h2>

    <%!private List<ru.bikert.test_task.abstractions.models.Rating> ratings;%>
    <%=ratings.get(1).getDate()%>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Фильм</th>
            <th>Рейтинг</th>
            <th>Количество голосов</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>John</td>
            <td>Doe</td>
            <td>john@example.com</td>
        </tr>

        </tbody>
    </table>
</div>
<div class="container mt-3">
    <button type="button" class="btn btn-primary" id="myBtn">Settings</button>

    <!-- The Modal -->
    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Settings</h4>
                    <button type="button" class="close" data-dismiss="modal">×</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="/top">
                        <div class="form-group">
                            <label for="limit">Limit:</label>
                            <input type="" class="form-control" id="limit">
                        </div>
                        <div class="form-group">
                            <label for="date">Date:</label>
                            <input type="date" class="form-control" id="date">
                        </div>
                    </form>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>

            </div>
        </div>
    </div>

</div>

<script>
    $(document).ready(function(){
        $("#myBtn").click(function(){
            $("#myModal").modal();
        });
    });
</script>
</body>
</html>