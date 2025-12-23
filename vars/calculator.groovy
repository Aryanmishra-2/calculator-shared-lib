import com.company.utils.*

def cleanWorkspace() {
    new CleanWorkspace(this).run()
}

def add(a, b) {
    def res = CalculatorUtils.add(a as int, b as int)
    saveToArtifact("Add: ${res}")
    return res
}

def sub(a, b) {
    def res = CalculatorUtils.sub(a as int, b as int)
    saveToArtifact("Sub: ${res}")
    return res
}

def mul(a, b) {
    def res = CalculatorUtils.mul(a as int, b as int)
    saveToArtifact("Mul: ${res}")
    return res
}

def div(a, b) {
    def res = CalculatorUtils.div(a as int, b as int)
    saveToArtifact("Div: ${res}")
    return res
}

def saveToArtifact(String content) {
    sh """
        mkdir -p artifacts
        echo "${content}" >> artifacts/result.txt
    """
}

def publishReport(status, recipient) {
    new com.company.utils.PublishReport(this).send(status, recipient)
}
