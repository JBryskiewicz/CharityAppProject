document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});

const bags = document.querySelector('#bags-input');
bags.addEventListener('input', function (event) {
    document.querySelector('#quantity-sum').innerText = bags.value;
})

const street = document.querySelector('#street-input');
street.addEventListener('input', function (event) {
    document.querySelector('#street-sum').innerText = street.value;
})

const city = document.querySelector('#city-input');
city.addEventListener('input', function (event) {
    document.querySelector('#city-sum').innerText = city.value;
})

const zipcode = document.querySelector('#zipcode-input');
zipcode.addEventListener('input', function (event) {
    document.querySelector('#zipcode-sum').innerText = zipcode.value;
})

const comment = document.querySelector('#comment-input');
comment.addEventListener('input', function (event) {
  document.querySelector('#comment-sum').innerText = comment.value;
})

const date = document.querySelector('#date-input');
date.addEventListener('input', function (event) {
  document.querySelector('#date-sum').innerText = date.value;
})

const time = document.querySelector('#time-input');
time.addEventListener('input', function (event) {
  document.querySelector('#time-sum').innerText = time.value;
})

const categoriesSummary = document.querySelector("#category-sum");
const institutionSummary = document.querySelector("#institution-sum");

document.querySelector("#step-4").addEventListener("click", () => {
  console.log("event whdoaahidiajh");
  categoriesSummary.innerHTML = getSelectedGifts();
  institutionSummary.innerHTML = getSelectedInstitution();
})

const giftsContainer = Array.from(document.querySelectorAll("#category-input > div")).slice(0,-1);
const gifts = giftsContainer.map(gift => {
  const id = gift.querySelector("label > input").value;
  const isChecked = () => gift.querySelector("label > input").checked;
  const text = gift.querySelector("label > .description").innerHTML;

  return {id, text, isChecked}
})

const getSelectedGifts = () => gifts.filter(gift => gift.isChecked()).map(gift => gift.text).join(',');

const institutionContainer = Array.from(document.querySelectorAll("#institution-input > div")).slice(0,-1);
const getSelectedInstitution = () => {
  const institution = institutionContainer.find(institution => institution.querySelector("label > input").checked);
  return institution ? institution.querySelector(".title").innerHTML : "Nikt kurwa XDDD"
}