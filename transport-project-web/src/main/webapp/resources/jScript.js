//===========================================
// Gloval vars ==============================
//===========================================
var $cargoCount = 0;
var $rpList = [];
var $ordinal = 0;
var	$loadList = [];
var $loadListCurrentRp = [];
var $deliverListCurrentRp = [];
var	$deliverList = [];
var $deliverListToNextPage = [];
var $allCargo = [];
var $order = {};
var checkedBoxesArray = [];

//var $testArray = [];

//$loadList.push({"name":"qwe","weight":"123"});
//$loadList.push({"name":"wer","weight":"234"});
//$loadList.push({"name":"try","weight":"345"});
//$loadList.push({"name":"try","weight":"345"});

//alert($testArray);

//$('#added tr:last').after(viewArrayAsTable($testArray));
//$('#added tbody').append(viewArrayAsTable($testArray));


////////////////////////////////////////
////////////////////////////////////////

//                  CLICK BUTTON SECTION

////////////////////////////////////////
////////////////////////////////////////
//============================================
// Click button "ADD"=========================
//============================================
$(function() {
    $('#addCargo').on('click', function() {

        var $cargo = {};
        var $cargoList = [];

        //var $name = $('#cargoName').val();
        //var $weight = $('#cargoWeight').val();
        //alert($name + ' ' + $weight);
        //$name.val($weight.val());


        $cargoCount++;

        // make cargo object ----------------------------------
        $cargo.name = $('#cargoName').val();
        $cargo.weight = $('#cargoWeight').val();
        $cargo.number = $cargoCount;
        $loadListCurrentRp.push($cargoCount);
        //alert($cargo.name);

        // add to arrays ---------------------------------
        $cargoList.push($cargo);
        $loadList.push($cargo);
        $deliverList.push($cargo);
        $allCargo.push($cargo);

        console.log('Cargo: $loadList = ' + JSON.stringify($loadList));

        // make loadList table ------------------------------------
        //+ $('#added tbody').append(viewArrayAsTable($cargoList));

        //var $cargoRowAdd = '<tr><td class="cagro-name">' +
        //								$name +
        //              '</td><td class="cagro-weight">' +
        //              $weight +
        //              '</td></tr>';

        //$('#added tr:last').after($cargoRowAdd);

        // make deliverList table -----------------------------
        // $('#deliverTable tbody').html(viewArrayAsTableWithCheckbox($loadList));

        //+ $('#deliverTable tbody').html(viewArrayAsTableWithCheckbox($deliverList));

        //var $cargoRowDeliver = '<tr><td><input id="ckid" 		class="ck"type="checkbox"/>//</td><td class="cargo-name">' +
//                            $name +
//                            '</td><td class="cargo-weight">' +
//                            $weight +
//                            '</td</tr>';

//    $('#delivery tr:last').after($cargoRowDeliver);

        // $("#testDiv").append(JSON.stringify($cargoList) + "<br/>");
        $("#testDiv").append("$cargoCount = " + $cargoCount + "<br/>");

        $('#cargoName').val("");
        $('#cargoWeight').val("");

    });
});



//======================================
// Click button "SAVE ROUTEPOINT" ===============
//======================================
$(function() {
    $("#addRp").on("click", function() {

    // vars
        var $rp = {};
        var $cargoNumbersArray = [];

        // $("#testDiv").append($cityList + "<br/>");
        // $loadList = tableWithCheckboxToArray("deliverTable");

        console.log('RP: $loadList = ' + JSON.stringify($loadList));

        $ordinal++;


    // process checkbox
        var testArrayChecked = [];
        var testArrayUnchecked = [];

        var valuesChecked = getCheckedBoxes('ck');
        var valuesUnchecked = getUncheckedBoxes('ck');

        console.log("$loadList = " + JSON.stringify($loadList));
        console.log("valuesChecked = " + JSON.stringify(valuesChecked));
        console.log("valuesUnchecked = " + JSON.stringify(valuesUnchecked));

        if (valuesChecked != null) {
        for(var i = 0; i < valuesChecked.length; i++) {
            testArrayChecked.push($deliverListToNextPage[valuesChecked[i]])
        }}

        if (valuesUnchecked != null) {
        for(var i = 0; i < valuesUnchecked.length; i++) {
            testArrayUnchecked.push($deliverListToNextPage[valuesUnchecked[i]])
        }}

        console.log("testArrayChecked = " + JSON.stringify(testArrayChecked));
        console.log('testArrayUnchecked = ' + JSON.stringify(testArrayUnchecked));
        // console.log("deliverTable = " + JSON.stringify(tableWithCheckboxToArray("deliverTable")));

    // set rp fields
        // $rp.city = $cityList;
        // $rp.loadList=$loadList;
        // $rp.loadList=$cargoNumbersArray;
        $rp.city = $("#cityList").val();

        // make Rp loadList
        $rp.loadList = [];
        for (var i = 0; i < $loadList.length; i++) {
            $rp.loadList.push($loadList[i].number);
        }

        // console.log('RP: $cargoNumbersArray = ' + JSON.stringify($cargoNumbersArray));
        // $rp.loadList = $loadList;

        // make Rp loadList
        $rp.deliverList = [];
        for (var i = 0; i < testArrayChecked.length; i++) {
            $rp.deliverList.push(testArrayChecked[i].number);
        }
        // $rp.deliverList = testArrayChecked;

        $rp.ordinal = $ordinal;

        console.log('RP: $rp = ' + JSON.stringify($rp));

    // put current Rp in RpList in Order
        $rpList.push($rp);

        console.log('RPList: $rpList = ' + JSON.stringify($rpList));

    // clear all inputs
        $("#cityList").val("");
        $('#cargoName').val("");
        $('#cargoWeight').val("");

        // clear #loadTable except first row
        //$("#loadTable").find("tr:gt(0)").remove();
        //$("#deliverTable2").find("tr:gt(0)").remove();



    // make new Rp array
        $deliverListToNextPage = [];
        $deliverListToNextPage = testArrayUnchecked;
        for(var i = 0; i < $loadList.length; i++) {
            $deliverListToNextPage.push($loadList[i]);
        }

        console.log('$deliverListToNextPage = ' + JSON.stringify($deliverListToNextPage));


    // clear arrays
        // $deliverList = $deliverListToNextPage;
        $loadList = [];
        // $loadListCurrentRp = [];
        // $deliverListCurrentRp = [];

    // print deliveru table
        $('#deliverTable tbody').html(viewArrayAsTableWithCheckbox($deliverListToNextPage));

    });
});

//======================================
// Click button "SAVE ORDER" ================
//======================================
$(function() {
    $("#ready").on("click", function() {

        $order.uid = 123;
        $order.cargoList = $allCargo;
        $order.rpList = $rpList;
        $order.status = 1;

        console.log('Order: $order = ' + JSON.stringify($order));

        // $("#testDiv").append("$order = " + JSON.stringify($order) + "<br/>");

//	$.get("/order/add/test", function($order){
//  		alert("Data: " + data);
//	});

        //$.get("/order/add/test", 'order=' + JSON.stringify($order)).done(function(data) {
            //alert(data);
        //}) ;

        $('#orderText').val(JSON.stringify($order));


    });
});

//=======================================
// Click button "TEST" ==================
//=======================================
$(function() {
    $("#test").on("click", function() {

        // var testArrayChecked = [];
        // var testArrayUnchecked = [];
        //
        // var valuesChecked = getCheckedBoxes('ck');
        // var valuesUnchecked = getUncheckedBoxes('ck');
        //
        // alert(JSON.stringify($loadList));
        // alert(JSON.stringify(valuesChecked));
        // alert(JSON.stringify(valuesUnchecked));
        //
        // for(var i = 0; i < valuesChecked.length; i++) {
        //     testArrayChecked.push($loadList[valuesChecked[i]])
        // }
        //
        // for(var i = 0; i < valuesUnchecked.length; i++) {
        //     testArrayUnchecked.push($loadList[valuesUnchecked[i]])
        // }
        //
        // alert(JSON.stringify(testArrayChecked));
        // alert(JSON.stringify(testArrayUnchecked));
        //
        // console.log(tableWithCheckboxToArray("deliverTable"));
    });
});


////////////////////////////////////////
////////////////////////////////////////

//               	DISABLE BUTTON SECTION

////////////////////////////////////////
////////////////////////////////////////
//===========================
// Disable SAVE CARGO button
//===========================
// $(function() {
//
//     $('#addCargo').attr('disabled', true);
//
//     $("#cargoName").on("change", function(){
//         var value = $.trim($("#cargoName").val());
//
//         if(value.length==0) {
//             $('#addCargo').attr('disabled', true);
//         }
//         else {
//             $('#addCargo').attr('disabled', false);
//         }
//     });
//
//     $("#cargoWeight").on("change", function(){
//         var value = $.trim($("#cargoWeight").val());
//
//         if(value.length==0) {
//             $('#addCargo').attr('disabled', true);
//         }
//         else {
//             $('#addCargo').attr('disabled', false);
//         }
//     });
// });

setInterval(function () {
    if (($.trim($("#cargoName").val())).length > 0
        &&
        ($.trim($("#cargoWeight").val())).length > 0)
    {
        $("#addCargo").prop('disabled', false);
    } else {
        $("#addCargo").prop('disabled', true);
    }
}, 500);

//===========================
// Disable SAVE RP button
//===========================
// $(function () {
//     $('#addRp').prop('disabled', true);
//     $("#cityList").on("change", function () {
//         var value = $.trim($("#cityList").val());
//         if (value.length == 0) {
//             $('#addRp').prop('disabled', true);
//         }
//         else {
//             $('#addRp').prop('disabled', false);
//         }
//     });
// });
setInterval(function () {
    if (
        !$('#cityList').val()
        // ||
    // ($ordinal == 0) ||
    //($cargoCount!=0))
    // $deliverListToNextPage.length != 0
    )
    {
        //alert("empty!");
        $("#addRp").prop('disabled', true);
    } else {
        $("#addRp").prop('disabled', false);
    }
}, 500);



//========================================
// Disable SAVE ORDER button if order not ready
//========================================
setInterval(function () {
    if (
        // !$('#cityList').val() ||
        ($ordinal == 0) ||
        //($cargoCount!=0))
        $deliverListToNextPage.length != 0)
    {
        //alert("empty!");
        $("#ready").prop('disabled', true);
    } else {
        $("#ready").prop('disabled', false);
    }
}, 500);


////////////////////////////////////////
////////////////////////////////////////

//               							UTILITIES

////////////////////////////////////////
////////////////////////////////////////





//======================================================
// View Array as table function
//======================================================
function viewArrayAsTable(myArray) {
    var result;
    //var result = "<table border=1>";

    // make head row with titles
//    result += "<tr>";
//    if (myArray.length>0){
//      	var x = myArray[0];
//     	var y = Object.keys(x);
//      	for(var j=0; j<y.length; j++){
//        	result += "<td><b>" + y[j] + "</b></td>";
//      	}
//	}

    // make table
    //result += "</tr>";

    if ( myArray == null || myArray.length == 0) {
        return result = '';
    }

    for (var i = 0; i < myArray.length; i++) {
        result += "<tr>";
        result += "<td class='cargo-name'>" + myArray[i].name + "</td>";
        result += "<td class='cargo-weight'>" + myArray[i].weight + "</td>";
        result += "</tr>";
    }
    //result += "</table>";
    return result;
};


// Original function------------------
//function viewArrayAsTable(myArray) {
//    var result;
//    var result = "<table border=1>";
//    for(var i=0; i<myArray.length; i++) {
//        result += "<tr>";
//        for(var j=0; j<myArray[i].length; j++){
//            result += "<td>"+myArray[i][j]+"</td>";
//        }
//        result += "</tr>";
//    }
//   result += "</table>";
//    return result;
//}


//======================================================
// View Array as table function with checkbox column
//======================================================
function viewArrayAsTableWithCheckbox(myArray) {
    var result;
    //var result = "<table border=1>";

    if ( myArray == null || myArray.length == 0) {
        return result = '';
    }

    for (var i = 0; i < myArray.length; i++) {
        result += "<tr>";
        result += "<td><input id='ckid' class='ck' type='checkbox' data-index='";
        result += i;
        result += "'></input></td>";
        result += "<td class='cargo-name'>" + myArray[i].name + "</td>";
        result += "<td class='cargo-weight'>" + myArray[i].weight + "</td>";
        result += "</tr>";
    }
    //result += "</table>";
    return result;
};

//======================================================
// Get array from HTML table
//======================================================
function tableToArray(tableId) {
    var TableData = [];
    $('#' + tableId + ' tbody tr').each(function (row, tr) {
        TableData[row] = {
            "name": $(tr).find('td:eq(0)').text()
            , "weight": $(tr).find('td:eq(1)').text()
        }
    });
    //TableData.shift();  // first row is the table header - so remove
    return TableData;
}


//======================================================
// Get array from HTML table with checkbox in first column
//======================================================
function tableWithCheckboxToArray(tableId) {
    var TableData = [];
    $('#' + tableId + ' tbody tr').each(function (row, tr) {
        TableData[row] = {
            "name": $(tr).find('td:eq(1)').text()
            , "weight": $(tr).find('td:eq(2)').text()
        }
    });
    //TableData.shift();  // first row is the table header - so remove
    return TableData;
}


//======================================================
// Hide div for deliveryList cause it is first rp ======
//======================================================
//if($ordinal==1){
//	$(".ck").prop('disabled', true);
//} else {
//	$(".ck").prop('disabled', false);
//}

//$("#addRp").prop('disabled', true);
//$("#addRp").prop('disabled', false);


//========================================================
// Checkbox is clicked event. Make deliverList ===========
//========================================================
// $(document).on('click', '.ck', function () {
//
//     var $cargo = {};
//
//     if (this.checked) {
//
//         // checkedBoxes.push()
//         var $row = $(this).closest("tr");    // Find the row
//         var $name = $row.find(".cargo-name").text(); // Find the name
//         var $weight = $row.find(".cargo-weight").text(); // Find the weight
//        // $row.remove();
//
//         //alert($(this).data("index"));
//
//         //alert();
//         //children('td.two').text();
//
//         $cargo.name = $name;
//         $cargo.weight = $weight;
//
//         //alert(JSON.stringify($cargo));
//         //alert($name);
//
//         $deliverList.push($cargo);
//
//
//         //  if count=0 then all cargo are delivered
//         if ($cargoCount > 0) {
//             $cargoCount--;
//         }
//
//         $("#testDiv").append("$cargoCount = " + $cargoCount + "<br/>");
//
//         // Make deliver at this RP table ----------------------------
//         $('#deliverTable2 tbody').html(viewArrayAsTable($deliverList));
//     }
//
// //  // Get all unchecked rows ------------
// //  if (this.unchecked) {
// //	var $row = $(this).closest("tr");    // Find the row
// //	var $name = $row.find(".cargo-name").text(); // Find the name
// // 	var $weight = $row.find(".cargo-weight").text(); // Find the weight
// //  }
//
// });





//=====================================================
//$(function() {
function f() {
    $('#cargoWeight').on('blur', function () {
        if ($('#cargoWeight').val() == '') {
            $('#test').prop('disabled', true);
        } else {
            $('#test').prop('disabled', false);
        }
        $('#testInput').val($('#cargoWeight').val());

    });
}

//=====================================================
// VIEW array as TABLE
//=====================================================
$(function(){
	setInterval(function(){
  	 //$('#test').prop('disabled', true);

        $('#loadTable tbody').html(viewArrayAsTable($loadList));
        $('#deliverTable2 tbody').html(viewArrayAsTable($deliverListCurrentRp));

        console.log("check");
  }, 200);
});



//});


//===========================================================
//$(".use-address").click(function() {
//    var $row = $(this).closest("tr");    // Find the row
//    var $text = $row.find(".nr").text(); // Find the text
//
//    // Let's test it out
//    alert($text);
//});


// getCheckedBoxes array=====================================
function getCheckedBoxes(chkboxName) {
    var checkboxes = document.getElementsByClassName(chkboxName);
    var checkboxesChecked = [];
    // loop over them all
    for (var i = 0; i < checkboxes.length; i++) {
        // And stick the checked ones onto an array...
        if (checkboxes[i].checked) {
            checkboxesChecked.push(i);
        }
    }
    // Return the array if it is non-empty, or null
    return checkboxesChecked.length > 0 ? checkboxesChecked : null;
}

function getUncheckedBoxes(chkboxName) {
    var checkboxes = document.getElementsByClassName(chkboxName);
    var checkboxesChecked = [];
    // loop over them all
    for (var i = 0; i < checkboxes.length; i++) {
        // And stick the checked ones onto an array...
        if (!checkboxes[i].checked) {
            checkboxesChecked.push(i);
        }
    }
    // Return the array if it is non-empty, or null
    return checkboxesChecked.length > 0 ? checkboxesChecked : null;
}



