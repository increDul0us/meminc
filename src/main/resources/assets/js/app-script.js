if($('[id="template_id"]').length) {
    let url = window.location.href;
    let id = url.substring(url.lastIndexOf('/') + 1);
    let memes = $('#all_memes').text()|| "[]";
    memes = JSON.parse(memes);
    $("#template_image").attr("src", memes[0].url);
    let template_id = $('#template_id');
    let options = []
    memes.map((meme) => {
        options.push({text:meme.name, value:meme.id})
    })
    template_id.selectize({
        options,
        create: false,
        preload: true,
        allowEmptyOptions: false,
        closeAfterSelect: true,
        onInitialize: function() {
            this.setValue(id);
        },
        onChange: function(value) {
            $("#miminc_image").removeClass("d-none");
            $("#miminc_image").addClass("d-none");
            let image = memes.find(meme=>meme.id == value);
            $("#template_image").attr("src", image.url);
        }
    });
}
$('#miminc_it').click(function (e) {
    e.preventDefault();
    let data = $('#miminc_it_form :input');
    var values = {};
    data.each(function() {
        values[this.id] = $(this).val();
    });
    if(!values.template_id) return $('#miminc_it').attr('disabled', false);
    values = JSON.stringify(values);
    $('#miminc_it').attr('disabled', true);
    $.ajax({ url: '/api/miminc', method: 'post', data:values}).done(function (data) {
        $('#miminc_it').attr('disabled', false);
        $("#miminc_image").attr("src", data.url);
        $("#miminc_image").removeClass("d-none");
        $("#miminc_image").wrap('<a href="' +data.url + '" download="meminc-' +new Date() + '" target="_blank"/>')
    });
});
