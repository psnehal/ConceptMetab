package conceptmetab



import org.junit.*
import grails.test.mixin.*

@TestFor(Compound_sourcesController)
@Mock(Compound_sources)
class Compound_sourcesControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/compound_sources/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.compound_sourcesInstanceList.size() == 0
        assert model.compound_sourcesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.compound_sourcesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.compound_sourcesInstance != null
        assert view == '/compound_sources/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/compound_sources/show/1'
        assert controller.flash.message != null
        assert Compound_sources.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/compound_sources/list'

        populateValidParams(params)
        def compound_sources = new Compound_sources(params)

        assert compound_sources.save() != null

        params.id = compound_sources.id

        def model = controller.show()

        assert model.compound_sourcesInstance == compound_sources
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/compound_sources/list'

        populateValidParams(params)
        def compound_sources = new Compound_sources(params)

        assert compound_sources.save() != null

        params.id = compound_sources.id

        def model = controller.edit()

        assert model.compound_sourcesInstance == compound_sources
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/compound_sources/list'

        response.reset()

        populateValidParams(params)
        def compound_sources = new Compound_sources(params)

        assert compound_sources.save() != null

        // test invalid parameters in update
        params.id = compound_sources.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/compound_sources/edit"
        assert model.compound_sourcesInstance != null

        compound_sources.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/compound_sources/show/$compound_sources.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        compound_sources.clearErrors()

        populateValidParams(params)
        params.id = compound_sources.id
        params.version = -1
        controller.update()

        assert view == "/compound_sources/edit"
        assert model.compound_sourcesInstance != null
        assert model.compound_sourcesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/compound_sources/list'

        response.reset()

        populateValidParams(params)
        def compound_sources = new Compound_sources(params)

        assert compound_sources.save() != null
        assert Compound_sources.count() == 1

        params.id = compound_sources.id

        controller.delete()

        assert Compound_sources.count() == 0
        assert Compound_sources.get(compound_sources.id) == null
        assert response.redirectedUrl == '/compound_sources/list'
    }
}
