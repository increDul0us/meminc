<#include "header.ftl">
  <div class="site-section" data-aos="fade">
    <div class="container-fluid">

      <div class="row justify-content-center">
        <div class="col-md-7">
          <div class="row mb-5">
            <div class="col-12 ">
              <h2 class="site-section-heading text-center"><strong>MEMINC</strong> This</h2>
            </div>
          </div>

          <div class="row">
            <div class="col-lg-6 mb-5">
              <form id= "miminc_it_form" action="/add" method="post">
                <div class="row form-group">
                <div class="col-md-12">
                    <span class="text-black d-none" id="all_memes">${memes}</span>
                    <label class="text-black" for="template_id">Select Image</label>
                    <select id="template_id" class="form-control" required></select>
                </div>
                </div>
                <div class="row form-group">
                <div class="col-md-12">
                    <label class="text-black" for="text0">Top Text</label>
                    <input type="text" id="text0" class="form-control">
                </div>
                </div>
                <div class="row form-group">
                <div class="col-md-12">
                    <label class="text-black" for="text1">Bottom Text</label>
                    <input type="text" id="text1" class="form-control">
                </div>
                </div>
                <div class="row form-group">
                  <div class="col-md-12">
                    <button id="miminc_it" type="submit" class="btn btn-primary py-2 px-4 text-white">Miminc it!!!</button>
                  </div>
                </div>
              </form>
            </div>
            <div class="col-lg-6 ml-auto">
                <div class="col-md-12">
                    <span class="text-black">Input:</span>
                    <div class="image-wrap">
                        <img class="temp_image" src="" alt="Image" id="template_image">
                    </div>
                </div>
                <div class="col-md-12">
                    <span class="text-black">Output:</span>
                    <div class="image-wrap">
                        <img class="temp_image d-none" src="" alt="Image" id="miminc_image">
                    </div>
                </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
<#include "footer.ftl">
