<%@include file="../../partials/header.jsp" %>

<div class="col-md-8 col-md-offset-2">
    <form action="/Relay" method="post">
        <input type="hidden" name="action" value="Topics"/>
        <input type="hidden" name="sub-action" value="new"/>

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Topic name"/>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" name="description" id="description"
                      placeholder="Topic description"></textarea>
        </div>

        <input type="submit" value="Save" class="btn btn-primary"/>
    </form>
</div>

<%@include file="../../partials/footer.jsp" %>