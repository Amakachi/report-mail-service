if ($("#backToBack").is(":checked")) {
    $("#b2b").show();
}
else if ($("#backToBack").is(":not(:checked)")) {
    $("#b2b").hide();
}

$("#backToBack").click(function () {
    if ($(this).is(":checked")) {
        $("#b2b").show();
    }
    else if ($(this).is(":not(:checked)")) {
        $("#b2b").hide();
    }
});
$("#rev").hide();
$("#revolving").click(function () {
    if ($(this).is(":checked")) {
        $("#rev").show();
    }
    else if ($(this).is(":not(:checked)")) {
        $("#rev").hide();
    }
});

if ($('#lcType').val() === "PCLPCN") {
    $("#partCleanline").show();
    $("#partConfirmed").show();
    $("#partUnconfirmed").hide();
} else if ($('#lcType').val() === "PCLPUC") {
    $("#partCleanline").show();
    $("#partConfirmed").hide();
    $("#partUnconfirmed").show();
}
else if ($('#lcType').val() === "PCNPUC") {
    $("#partCleanline").hide();
    $("#partConfirmed").show();
    $("#partUnconfirmed").show();
} else if ($('#lcType').val() === "UC") {
    $("#partCleanline").hide();
    $("#partConfirmed").hide();
    $("#partUnconfirmed").hide();
} else if ($('#lcType').val() === "CN") {
    $("#partCleanline").hide();
    $("#partConfirmed").hide();
    $("#partUnconfirmed").hide();
} else if ($('#lcType').val() === "CL") {
    $("#partCleanline").hide();
    $("#partConfirmed").hide();
    $("#partUnconfirmed").hide();
} else {
    $("#partCleanline").hide();
    $("#partConfirmed").hide();
    $("#partUnconfirmed").hide();
}

$('#lcType').on('change', function () {
    if ($(this).val() === "PCLPCN") {
        $("#partCleanline").show();
        $("#partConfirmed").show();
        $("#partUnconfirmed").hide();
    } else if ($(this).val() === "PCLPUC") {
        $("#partCleanline").show();
        $("#partConfirmed").hide();
        $("#partUnconfirmed").show();
    }
    else if ($(this).val() === "PCNPUC") {
        $("#partCleanline").hide();
        $("#partConfirmed").show();
        $("#partUnconfirmed").show();
    } else if ($(this).val() === "UC") {
        $("#partCleanline").hide();
        $("#partConfirmed").hide();
        $("#partUnconfirmed").hide();
    } else if ($(this).val() === "CN") {
        $("#partCleanline").hide();
        $("#partConfirmed").hide();
        $("#partUnconfirmed").hide();
    } else if ($(this).val() === "CL") {
        $("#partCleanline").hide();
        $("#partConfirmed").hide();
        $("#partUnconfirmed").hide();
    }
});

if ($('#concessionFlag').val() === "Y") {
    $("#concessionPercentage").show();
} else {
    $("#concessionPercentage").hide();
}

$('#concessionFlag').on('change', function () {
    if ($(this).val() === "Y") {
        $("#concessionPercentage").show();
    } else {
        $("#concessionPercentage").hide();
    }
});

$('#button_search_bb').click(function (e) {
    e.preventDefault();
    var options = {
        url: "/general/bank/find",
        title: 'Bank Search ',
        size: eModal.size.lg,
        buttons: [{
            text: 'Select Bank',
            style: 'info',
            close: true,
            click: function () {
                var usertab = $('#operation').DataTable();
                usertab.rows({
                    selected: true
                }).data().each(function (d) {
                    $('#beneficiaryBankName').val(d['institutionName']),
                        $('#beneficiarySwiftCode').val(d['swiftCode']),
                        $('#beneficiaryBankAddress').val(d['bankAddress'])
                });
            }
        }, {
            text: 'Close',
            style: 'default',
            close: true
        }],
    };
    eModal.ajax(options);
});
$('#button_search_ad').click(function (e) {
    e.preventDefault();
    var options = {
        url: "/general/bank/find",
        title: 'Bank Search ',
        size: eModal.size.lg,
        buttons: [{
            text: 'Select Bank',
            style: 'info',
            close: true,
            click: function () {
                var usertab = $('#operation').DataTable();
                usertab.rows({
                    selected: true
                }).data().each(function (d) {
                        $('#advBankName').val(d['institutionName']),
                        $('#advSwiftCode').val(d['swiftCode']),
                        $('#advBankAddress').val(d['bankAddress'])

                    populateNostroAcct(d['institutionCode'],d['branchId']);


                });
            }
        }, {
            text: 'Close',
            style: 'default',
            close: true
        }],
    };
    eModal.ajax(options);
});

function populateNostroAcct(bankCode,branchId) {

    $.ajax({
        type: 'POST',
        data: {bankCode: bankCode,branchId: branchId},
        url: "/general/populateNostroAcct",
        success: function (data) {
            var dd = data;
            var actNumArray = [];
            actNumArray = dd;
            var jj = $('#corrNostroAccount') ;
            jj.children('option:not(:first)').remove();
            for (var i = 0; i < actNumArray.length; i++) {
                jj.append($('<option>', {
                    value: actNumArray[i],
                    text: actNumArray[i]
                }));
            }
        }, error: function (amt) {

        }
    });
}

$('#button_search_expad').click(function (e) {
    e.preventDefault();
    var options = {
        url: "/general/bank/find",
        title: 'Bank Search ',
        size: eModal.size.lg,
        buttons: [{
            text: 'Select Bank',
            style: 'info',
            close: true,
            click: function () {
                var usertab = $('#operation').DataTable();
                usertab.rows({
                    selected: true
                }).data().each(function (d) {
                        $('#advBankName').val(d['institutionName']),
                        $('#advSwiftCode').val(d['swiftCode']),
                        $('#advBankAddress').val(d['bankAddress'])


                });
            }
        }, {
            text: 'Close',
            style: 'default',
            close: true
        }],
    };
    eModal.ajax(options);
});
$('#button_search_is').click(function (e) {
    e.preventDefault();
    var options = {
        url: "/general/bank/find",
        title: 'Bank Search ',
        size: eModal.size.lg,
        buttons: [{
            text: 'Select Bank',
            style: 'info',
            close: true,
            click: function () {
                var usertab = $('#operation').DataTable();
                usertab.rows({
                    selected: true
                }).data().each(function (d) {
                          $('#issuingBank').val(d['institutionName']),
                        $('#issuingBankSwift').val(d['swiftCode']),
                        $('#issuingBankAddr').val(d['bankAddress'])



                });
            }
        }, {
            text: 'Close',
            style: 'default',
            close: true
        }],
    };
    eModal.ajax(options);
});
$('#button_search_expis').click(function (e) {
    e.preventDefault();
    var options = {
        url: "/general/bank/find",
        title: 'Bank Search ',
        size: eModal.size.lg,
        buttons: [{
            text: 'Select Bank',
            style: 'info',
            close: true,
            click: function () {
                var usertab = $('#operation').DataTable();
                usertab.rows({
                    selected: true
                }).data().each(function (d) {
                        $('#issuingBank').val(d['institutionName']),
                        $('#issuingBankSwift').val(d['swiftCode']),
                        $('#issuingBankAddr').val(d['bankAddress'])

                    populateNostroAcct(d['institutionCode'],d['branchId']);


                });
            }
        }, {
            text: 'Close',
            style: 'default',
            close: true
        }],
    };
    eModal.ajax(options);
});
$('#button_search_ab').click(function (e) {
    e.preventDefault();
    var options = {
        url: "/general/bank/find",
        title: 'Bank Search ',
        size: eModal.size.lg,
        buttons: [{
            text: 'Select Bank',
            style: 'info',
            close: true,
            click: function () {
                var usertab = $('#operation').DataTable();
                usertab.rows({
                    selected: true
                }).data().each(function (d) {
                    $('#advBankName').val(d['institutionName']),
                        $('#advSwiftCode').val(d['swiftCode']),
                        $('#advBankAddress').val(d['bankAddress'])
                });
            }
        }, {
            text: 'Close',
            style: 'default',
            close: true
        }],
    };
    eModal.ajax(options);
});
$('#button_search_atb').click(function (e) {
    e.preventDefault();
    var options = {
        url: "/general/bank/find",
        title: 'Bank Search ',
        size: eModal.size.lg,
        buttons: [{
            text: 'Select Bank',
            style: 'info',
            close: true,
            click: function () {
                var usertab = $('#operation').DataTable();
                usertab.rows({
                    selected: true
                }).data().each(function (d) {
                    $('#advThrBankName').val(d['institutionName']),
                        $('#advThrSwiftCode').val(d['swiftCode']),
                        $('#advThrBankAddress').val(d['bankAddress'])
                });
            }
        }, {
            text: 'Close',
            style: 'default',
            close: true
        }],
    };
    eModal.ajax(options);
});