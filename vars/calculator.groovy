import com.company.utils.*

def cleanWorkspace() {
    new CleanWorkspace(this).run()
}

def add(a, b) {
    def res = CalculatorUtils.add(a as int, b as int)
    new ArtifactWriter(this).save("Add: ${res}")
    return res
}

def sub(a, b) {
    def res = CalculatorUtils.sub(a as int, b as int)
    new ArtifactWriter(this).save("Sub: ${res}")
    return res
}

def mul(a, b) {
    def res = CalculatorUtils.mul(a as int, b as int)
    new ArtifactWriter(this).save("Mul: ${res}")
    return res
}

def div(a, b) {
    def res = CalculatorUtils.div(a as int, b as int)
    new ArtifactWriter(this).save("Div: ${res}")
    return res
}

def publishReport(status, recipient) {
    new PublishReport(this).send(status, recipient)
}
