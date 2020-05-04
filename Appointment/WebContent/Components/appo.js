/**
 * 
 */
$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

$(document).on("click", "#bookingBtn", function(event) {
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	var status = validateAppoForm();

	if (status != true) {
		$("alertError").text(status);
		$("alertError").show();
		return;
	}

	var type = ($("#hidAppoIdSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "AppoAPI",
		type : type,
		data : $("#booingForm").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});
});

function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#appoGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidAppoIdSave").val("");
	$("#booingForm")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "AppoAPI",
		type : "DELETE",
		data : "appoId=" + $(this).data("appoid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});


function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();

			$("#appoGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


// UPDATE==========================================

$(document).on(
		"click",
		".btnUpdate",
		function(event) {

			$("#hidAppoIdSave").val(
					$(this).closest("tr").find('#hidReadAppoIdUpdate').val());

			$("#userId").val($(this).closest("tr").find('td:eq(0)').text());

			$("#docId").val($(this).closest("tr").find('td:eq(1)').text());

			$("#docSpec").val($(this).closest("tr").find('td:eq(2)').text());

			$("#hospId").val($(this).closest("tr").find('td:eq(3)').text());

			$("#date").val($(this).closest("tr").find('td:eq(4)').text());

		});

function validateAppoForm() {

	// CODE

	if ($("#userId").val().trim() == "") {

		return "Insert user id.";

	}

	if ($("#docId").val().trim() == "") {
		var x = document.forms["booingForm"]["userId"].value;
		if (x == "") {
			alert("User Id must be filled out");
			return false;
		}
		return "Insert Hospital Contact Number.";

	}

	if ($("#docSpec").val().trim() == "") {

		return "Insert doctor specialization.";

	}

	if ($("#hospId").val().trim() == "") {

		return "Insert hospital id.";

	}

	if ($("#date").val().trim() == "") {

		return "Insert date.";

	}

	return true;

}



