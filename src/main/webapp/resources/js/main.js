let X, Y, R;

$("#coordinates").click(graph);

function newR() {
    drawOnLoad();
    changeR();
}

function changeR() {
    document.getElementById("r1").innerHTML = String(R / 2);
    document.getElementById("r2").innerHTML = String(R);
    document.getElementById("r3").innerHTML = String(-R / 2);
    document.getElementById("r4").innerHTML = String(-R);
    document.getElementById("r5").innerHTML = String(R / 2);
    document.getElementById("r6").innerHTML = String(R);
    document.getElementById("r7").innerHTML = String(-R / 2);
    document.getElementById("r8").innerHTML = String(-R);
}

function drawOnLoad() {
    deleteDots();
    $("tbody tr").each(function () {
        const point = $(this);

        X = parseFloat(point.find("td:first-child").text());
        Y = parseFloat(point.find("td:nth-child(2)").text());
        R = parseFloat(document.getElementById("coordinates-form:r_input").value.replace(",", "."));

        drawDots();
    });
}

function checkResult(x, y, r) {
    let rectangle = false;
    let triangle = false;
    let circle = false;

    x = parseFloat(x);
    y = parseFloat(y);
    r = parseFloat(r);

    if (x >= 0 && x <= r / 2 && y >= 0 && y <= r) {
        rectangle = true;
    }

    if ((x >= r * (-1)) && x <= 0 && y >= 0 && (y <= x + r)) {
        triangle = true;
    }

    if (x >= -r && x <= 0 && y >= -r && y <= 0 && x * x + y * y <= r * r) {
        circle = true;
    }

    return rectangle || triangle || circle;
}

function graph(e) {
    R = document.getElementById("coordinates-form:r_input").value;
    R = R.replace(",", ".");

    if (isNaN(R)) {
        alert("Input correct R-value (double number in [1;4]!)");
        return;
    }
    
    let svgSize = 410;

    const position = $('svg').offset();
    let rowX = e.pageX - position.left;
    let rowY = e.pageY - position.top;

    X = ((-1 * (R / 75) * (svgSize / 2 - rowX)) / 2 - 10 * R / 75);
    Y = (((R / 75) * (svgSize / 2 - rowY)) / 2 + 10 * R / 75);

    $(".X").val(X.toFixed(2));
    $(".Y").val(Y.toFixed(2));
    $(".R").val(R);

    $(".submitGraph").click();

    drawDots();
}

function drawDots() {
    const color = checkResult(X, Y, R) ? 'green' : 'red';

    let size = 450;

    let coordinateX = size / 2 + ((X + (1 / 75)) / R) * 150;
    let coordinateY = size / 2 - ((Y - (1 / 75)) / R) * 150;

    const graph = $("#coordinates svg");
    const existingContent = graph.html();

    if (!isNaN(coordinateX) && !isNaN(coordinateY)) {
        const contentToInsert = `<circle class="dot" 
                                 r="3" 
                                 cx="${coordinateX}"
                                 cy="${coordinateY}"
                                 fill="${color}"/>`;
        graph.html(existingContent + contentToInsert);
    }
}

function getX() {
    let arr = [];
    if (document.getElementById("coordinates-form:j_idt16_input").checked === true) {
        arr.push(-2);
    }
    if (document.getElementById("coordinates-form:j_idt17_input").checked === true) {
        arr.push(-1.5);
    }
    if (document.getElementById("coordinates-form:j_idt18_input").checked === true) {
        arr.push(-1);
    }
    if (document.getElementById("coordinates-form:j_idt20_input").checked === true) {
        arr.push(-0.5);
    }
    if (document.getElementById("coordinates-form:j_idt21_input").checked === true) {
        arr.push(0);
    }
    if (document.getElementById("coordinates-form:j_idt22_input").checked === true) {
        arr.push(0.5);
    }
    if (document.getElementById("coordinates-form:j_idt24_input").checked === true) {
        arr.push(1);
    }
    if (document.getElementById("coordinates-form:j_idt25_input").checked === true) {
        arr.push(1.5);
    }
    if (document.getElementById("coordinates-form:j_idt26_input").checked === true) {
        arr.push(2);
    }
    return arr;
}

function drawDotsFromForm() {
    let arr = getX();

    for (let x of arr) {
        X = x;
        Y = document.getElementById("coordinates-form:y").value;
        R = document.getElementById("coordinates-form:r_input").value.replace(",", ".");
        drawDots();
    }
}

function deleteDots(){
    $(".dot").remove();
}

window.onload = function () {
    drawOnLoad();
    changeR();
}